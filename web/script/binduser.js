/**
 * Created by onedayrex on 2016/3/5.
 */
function bindusermsg(socket){
    var user = getCookie("user");
    socket.onopen = function () {
        socket.send(JSON.stringify({
            statu:0,
            username:user
        }));
    }

    //sendmsg(JSON.stringify({
    //    statu:0,
    //    username:user
    //}));
}

function sendmsg(message){
        waitForConnection(function () {
            socket.send(message);
        }, 1000);
}

function waitForConnection(callback,interval){
        if (socket.readyState === 1) {
            callback();
        } else {
            var that = this;
            // optional: implement backoff for interval here
            setTimeout(function () {
                that.waitForConnection(callback, interval);
            }, interval);
        }
}