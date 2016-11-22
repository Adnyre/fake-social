var replaceUrl = function (t) {
    var httpExpression = /(^|\s)(https?:\/\/[^\s.]+[^\s]+)/gi;
    var wwwExpression = /(^|\s)(www\.[^\s]+)/gi;
    t = t.replace(httpExpression, '$1<a href="$2" class="matched">$2</a>');
    return t.replace(wwwExpression, '$1<a href="http://$2" class="matched">$2</a>');
}