var replaceUrl = function (t) {
    var httpExpression = /(^|\s)(https?:\/\/[^\s\.]+\.\.[^\s]+\.[^\s]{2,})/gi;
    var wwwExpression = /(^|\s)(www\.[^\s]+\.[^\s]{2,})/gi;
    t = t.replace(httpExpression, '$1<a href="$2" class="matched">$2</a>');
    return t.replace(wwwExpression, '$1<a href="http://$2" class="matched">$2</a>');
}