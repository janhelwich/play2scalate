navigator.sayswho= (function(){
    var N= navigator.appName, ua= navigator.userAgent, tem;
    var M= ua.match(/(opera|chrome|safari|firefox|msie)\/?\s*(\.?\d+(\.\d+)*)/i);
    if(M && (tem= ua.match(/version\/([\.\d]+)/i))!= null) M[2]= tem[1];
    M= M? [M[1], M[2]]: [N, navigator.appVersion, '-?'];

    return M;
})();

window.isChrome = function() {
    return navigator.sayswho[0] == "Chrome";
}
window.isSafari = function() {
    return navigator.sayswho[0] == "Safari";
}
window.isFirefox = function() {
    return navigator.sayswho[0] == "Firefox";
}

window.initButtons = function() {
    $('#ab-btn').click(function () {
        if (timeA == 0) {
            timeA = playerMain.currentTime;
            $('#abBtnA').css('color', 'black');
        } else if (timeB == 0) {
            timeB = playerMain.currentTime;
            playerMain.addEventListener("timeupdate", jumpToPointAIfAfterB);
            playerMain.currentTime = timeA;
            $('#abBtnB').css('color', 'black');
        } else {
            timeA = timeB = 0;
            playerMain.removeEventListener("timeupdate", jumpToPointAIfAfterB);
            $('#abBtnA').css('color', 'white');
            $('#abBtnB').css('color', 'white');
        }
    });

    $('#btn25').click(function () {
        playerMain.playbackRate = 0.25;
        player1.playbackRate = 0.25;
        player2.playbackRate = 0.25;
    });
    $('#btn50').click(function () {
        playerMain.playbackRate = 0.25;
        player1.playbackRate = 0.5;
        player2.playbackRate = 0.5;
    });
    $('#btn75').click(function () {
        playerMain.playbackRate = 0.75;
        player1.playbackRate = 0.75;
        player2.playbackRate = 0.75;
    });
    $('#btn100').click(function () {
        playerMain.playbackRate = 1;
        player1.playbackRate = 1;
        player2.playbackRate = 1;
    });

    function jumpToPointAIfAfterB() {
        if (playerMain.currentTime >= timeB) {
            playerMain.currentTime = timeA;
        }
        return false;
    }
}

function select_option(selectId, i) {
    return $('select' + selectId + ' option[value="' + i + '"]').html();
}

