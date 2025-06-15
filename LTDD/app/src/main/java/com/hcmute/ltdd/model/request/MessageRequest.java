package com.hcmute.ltdd.model.request;


public class MessageRequest {
    private Long receiverId;
    private String messageType;   // TEXT hoặc POST
    private String content;
    private Long sharedPostId;

    public MessageRequest() {

    }

    public MessageRequest(Long receiverId, String content) {
        this.receiverId = receiverId;
        this.content = content;
        this.messageType = "TEXT"; // default nếu cần
        this.sharedPostId = null;  // để trống nếu không chia sẻ bài viết
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getSharedPostId() {
        return sharedPostId;
    }

    public void setSharedPostId(Long sharedPostId) {
        this.sharedPostId = sharedPostId;
    }
}
