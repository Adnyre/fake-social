var monthsShort = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
var colors =['aquamarine', 'bisque', 'pink', 'plum'];
var users = [];

setInterval(function(){
    var loadedMsgIds = getLoadedMessageIds();
    $.getJSON("message_id", function(data){

        $.each(data, function(ind, messageId) {
            if (!loadedMsgIds.includes("" + messageId)) {
                $.ajax({
                  dataType: "json",
                  url: "message/" + messageId,
                  success: function(message){
                      if (users.indexOf(message.user) == -1) {
                          users.push(message.user);
                      }
                      var messageTag = "<div class=\"message col-lg-12\" id=\"MSG" + message.id +
                          "\"> <div class=\"message-author\">" + message.user +
                          "</div> <div class=\"row\"> <div class=\"col-lg-10 simple-message list-group-item\" style=\"background: " + colors[users.indexOf(message.user)] +
                          ";\">" + replaceUrl(message.text) +
                          "</div><div class=\"col-lg-2 message-time\">" + getTime(message.time) + "</div> </div> </div>";
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

var getTime = function (time) {
    var now = new Date();
    if (now.getFullYear() != time.date.year) {
        return "year " + time.date.year;
    }
    if (now.getMonth() + 1 != time.date.month) {
        return monthsShort[time.date.month - 1];
    }
    if (now.getDate() != time.date.day) {
        return monthsShort[time.date.month - 1] + time.date.day;
    }
    return time.time.hour + ":" + time.time.minute + ":" + time.time.second;
}