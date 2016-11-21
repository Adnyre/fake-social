setInterval(function(){
    var loadedMsgIds = getLoadedMessageIds();
    $.getJSON("message_id", function(data){

        $.each(data, function(ind, messageId) {
            if (!loadedMsgIds.includes("" + messageId)) {
                $.ajax({
                  dataType: "json",
                  url: "message/" + messageId,
                  success: function(message){
                           var messageTag = "<div class=\"message row\" id=\"MSG" + message.id + "\"><div class=\"message-author\">" + message.id + ": " + message.user +
                                            "</div><div class=\"row\"><div class=\"col-lg-10 simple-message list-group-item\"style=\"background:pink;\">" +
                                            message.text + "</div><div class=\"col-lg-2 message-time\">" + message.time + "</div></div></div>";
                           $(".message-feed").append(messageTag);
                       },
                  async: false
                });
            }
        });
    });
}, 8000);

var getLoadedMessageIds = function() {
    var ids = [];
    $(".message").each(function(ind) {
        ids.push(this.id.substring(3));
    });
    return ids;
};


