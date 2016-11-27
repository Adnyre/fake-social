var monthsShort = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
var colors =['aquamarine', 'bisque', 'pink', 'plum'];
var users = [];

$(document).ready(function (e) {
    $('#source').click(function (e) { //Offset mouse Position
        var coords = {
             x: e.pageX - $(this).offset().left,
             y: e.pageY - $(this).offset().top
         };
        $('#messageText').val($('#messageText').val() + EmoticonHandler.emoticons[EmoticonHandler.getIndex(coords)]);
        $('#myModal').hide();
    });

    $('#sendBtn').click(function (e) {
        var rawText = $('#messageText').val();
        var now = new Date();
        var message = {
            time: {"date": {
                        "year": now.getFullYear(),
                        "month": now.getMonth() + 1,
                        "day": now.getDate()},
                   "time": {
                        "hour": now.getHours(),
                        "minute": now.getMinutes(),
                        "second": now.getSeconds(),
                        "nano": 0
                   }
            },
            user: "ME", //TODO
            text: rawText
        };

        var tag = "<div class=\"message col-lg-12\" id=\"MSG" + 0 +
          "\"> <div class=\"message-author\">" + message.user +
          "</div> <div class=\"row\"> <div class=\"col-lg-10 simple-message list-group-item\" style=\"background: " +
          colors[users.indexOf(message.user)] + ";\">" + Parser.replaceEmoticons(message.text) +
          "</div><div class=\"col-lg-2 message-time\">" + getTime(message.time) + "</div> </div> </div>";
        $(".message-feed").append(tag);
        $('#messageText').val("");

//        $.post("message", message,
//            function(data) {
//                //TODO!!
//
//            }, "json");
    });
});

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
                          ";\">" + Parser.replaceUrl(message.text) +
                          "</div><div class=\"col-lg-2 message-time\">" + getTime(message.time) + "</div> </div> </div>";
                      $(".message-feed").append(messageTag);

                      $("#MSG" + message.id + " a").each(function() {
                          Parser.checkLink(this);
                      });
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