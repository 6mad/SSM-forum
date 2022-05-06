package com.hubin.forum.app.manager;

import com.hubin.forum.app.support.IsLogin;
import com.hubin.forum.app.support.LoginUserContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import com.hubin.forum.api.model.PageRequestModel;
import com.hubin.forum.api.model.PageResponseModel;
import com.hubin.forum.api.request.comment.CommentCreateRequest;
import com.hubin.forum.api.response.comment.CommentPageResponse;
import com.hubin.forum.app.transfer.CommentTransfer;
import com.hubin.forum.common.enums.ErrorCodeEn;
import com.hubin.forum.common.model.PageResult;
import com.hubin.forum.common.support.CheckUtil;
import com.hubin.forum.common.support.EventBus;
import com.hubin.forum.domain.entity.BasePosts;
import com.hubin.forum.domain.entity.Comment;
import com.hubin.forum.domain.entity.User;
import com.hubin.forum.domain.repository.CommentRepository;
import com.hubin.forum.domain.repository.PostsRepository;
import com.hubin.forum.domain.repository.UserRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Hubin
 * @create 2021/11/6
 * @desc
 **/
@Component
public class CommentManager {

    @Resource
    private CommentRepository commentRepository;

    @Resource
    private PostsRepository postsRepository;

    @Resource
    private UserRepository userRepository;

    @IsLogin
    @Transactional(rollbackFor = Exception.class)
    public void create(CommentCreateRequest request) {
        BasePosts posts = postsRepository.get(request.getPostsId());
        CheckUtil.isEmpty(posts, ErrorCodeEn.POSTS_NOT_EXIST);

        Comment comment = CommentTransfer.toComment(request, LoginUserContext.getUser());

        if (!ObjectUtils.isEmpty(comment.getReplyId())) {
            Comment reply = commentRepository.get(comment.getReplyId());
            CheckUtil.isEmpty(reply, ErrorCodeEn.COMMENT_NOT_EXIST);
            CheckUtil.isFalse(reply.getPostsId().equals(request.getPostsId()), ErrorCodeEn.COMMENT_POSTS_NOT_EXIST);

            if (!ObjectUtils.isEmpty(reply.getReplyId())) {
                comment.setReplyReplyId(reply.getId());
                comment.setReplyId(reply.getReplyId());
            }
        }

        // 保存
        commentRepository.save(comment);

        // 增加帖子评论数
        postsRepository.increaseComments(posts.getId(), posts.getUpdateAt());

        Map<String, Object> msg = new HashMap<>();
        msg.put("commenter", LoginUserContext.getUser().getId());
        msg.put("comment", comment);
        EventBus.emit(EventBus.Topic.COMMENT_CREATE, msg);
    }

    public PageResponseModel<CommentPageResponse> page(PageRequestModel<Long> pageRequest) {
        PageResult<Comment> pageResult = commentRepository.page(pageRequest.getPageNo(), pageRequest.getPageSize(), pageRequest.getFilter());

        if (ObjectUtils.isEmpty(pageResult.getList())) {
            return PageResponseModel.build(pageResult.getTotal(), pageResult.getSize(), new ArrayList<>());
        }

        List<User> users = userRepository.queryByIds(pageResult.getList().stream()
                .map(Comment::getUserId)
                .collect(Collectors.toList()));

        List<CommentPageResponse> responses = CommentTransfer.toCommentPageResponses(pageResult.getList(), users, false);

        List<Comment> replies = commentRepository.queryInReplyIds(pageResult.getList().stream()
                .map(Comment::getId)
                .collect(Collectors.toSet()));
        if (ObjectUtils.isEmpty(replies)) {
            return PageResponseModel.build(pageResult.getTotal(), pageResult.getSize(), responses);
        }

        List<User> replyUsers = userRepository.queryByIds(replies.stream()
                .map(Comment::getUserId)
                .collect(Collectors.toList()));
        List<CommentPageResponse> replyComments = CommentTransfer.toCommentPageResponses(replies, replyUsers, true);
        responses.forEach(response -> {
            response.setReplies(replyComments.stream().filter(replyComment ->
                    replyComment.getReplyId().equals(response.getId()))
                    .collect(Collectors.toList()));
        });

        return PageResponseModel.build(pageResult.getTotal(), pageResult.getSize(), responses);
    }
}
