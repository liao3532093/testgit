/**
 * Created by vsofo on 2017/8/4.
 */
function initGoEasy() {
    // if (initGoEasy.unique !== undefined) {
    //     return initGoEasy.unique;
    // }
    this.goEasy = new GoEasy({
        appkey: 'BC-c371774a74464373b6712c54d389937f',
        onConnected: function () {
            console.log("成功连接GoEas");
            //LOG_I_R_B("成功连接GoEas");
        },
        onDisconnected: function () {
            console.log("与GoEasy连接断开");
        },
        onConnectFailed: function (error) {
            console.log("与GoEasy连接失败，错误编码：" + error.code + "错误信息：" + error.content);
        }
    });
    return this.goEasy;
}
function readMessage(sessionId, callback) {
    var obj = new initGoEasy();
    obj.goEasy.subscribe({  //接收消息
        channel: sessionId,
        onMessage: function (message) {
            //alert('收到：' + message.content);
            callback.call(this, message.content);
        },
        onSuccess: function () {
            //alert("Channel订阅成功。");
        },
        onFailed: function (error) {
            console.log("订阅失败, 错误编码：" + error.code + " 错误信息：" + error.content)
        }
    });
}
/***
 *  发送消息
 */
function sendMessage(sessionId, message, callback) {
    var obj = new initGoEasy();
    obj.goEasy.publish({
        channel: sessionId,
        message: message,
        onSuccess: function () {
            //alert("消息发布成功。");
            callback.call(this, '');
        },
        onFailed: function (error) {
            console.log("消息发送失败，错误编码：" + error.code + " 错误信息：" + error.content);
        }
    });
}