var replaceUrl = function (t) {
    var httpExpression = /(^|\s)(https?:\/\/[^\s.]+[^\s]+)/gi;
    var wwwExpression = /(^|\s)(www\.[^\s]+)/gi;
    t = t.replace(httpExpression, '$1<a href="$2" class="matched">$2</a>');
    return t.replace(wwwExpression, '$1<a href="http://$2" class="matched">$2</a>');
}

var checkLink = function (link) {
    var url = $(link).attr("href");
    $.ajax({
        method: "HEAD",
        url: url,
        success: function (data, textStatus, request) {
            if (request.getResponseHeader('content-type').startsWith("image/")) {
                $(link).html('<img border="0" src="' + url + '" width="100" height="100">');
            }
        }
    });
}




