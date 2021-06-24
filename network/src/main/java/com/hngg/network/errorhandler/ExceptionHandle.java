package com.hngg.network.errorhandler;

import com.google.gson.JsonParseException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import javax.net.ssl.SSLHandshakeException;

import retrofit2.HttpException;

/**
 * Date: 2020/11/23
 * Timer: 22:13
 * Author: nedhuo
 * Description:
 */
public class ExceptionHandle {
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNET_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;


    public static ResponseThrowable handleException(Throwable throwable) {
        ResponseThrowable ex;
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            ex = new ResponseThrowable(throwable, ERROR.HTTP_ERROR);
            switch (httpException.code()) {
                case UNAUTHORIZED:
                    ex.message = "操作未授权";
                    break;
                case FORBIDDEN:
                    ex.message = "请求被拒绝";
                    break;
                case NOT_FOUND:
                    ex.message = "资源不存在";
                    break;
                case REQUEST_TIMEOUT:
                    ex.message = "服务器执行超时";
                    break;
                case INTERNET_SERVER_ERROR:
                    ex.message = "服务器内部错误";
                    break;
                case SERVICE_UNAVAILABLE:
                    ex.message = "服务器不可用";
                    break;
                default:
                    ex.message = "网络错误";
                    break;
            }
            return ex;
        } else if (throwable instanceof ServerException) {
            ServerException serverException = (ServerException) throwable;
            ex = new ResponseThrowable(serverException, serverException.code);
            ex.message = serverException.message;
            return ex;
        } else if (throwable instanceof JsonParseException ||
                throwable instanceof JSONException ||
                throwable instanceof ParseException) {
            ex = new ResponseThrowable(throwable, ERROR.PARSE_ERROR);
            ex.message = "解析错误";
            return ex;
        } else if (throwable instanceof ConnectException) {
            ex = new ResponseThrowable(throwable, ERROR.NETWORK_ERROR);
            ex.message = "连接失败";
            return ex;
        } else if (throwable instanceof SSLHandshakeException) {
            ex = new ResponseThrowable(throwable, ERROR.SSL_ERROR);
            ex.message = "证书验证失败";
            return ex;
        } else if (throwable instanceof ConnectTimeoutException) {
            ex = new ResponseThrowable(throwable, ERROR.TIMEOUT_ERROR);
            ex.message = "连接超时";
            return ex;
        } else if (throwable instanceof SocketTimeoutException) {
            ex = new ResponseThrowable(throwable, ERROR.TIMEOUT_ERROR);
            ex.message = "连接超时";
            return ex;
        } else if (throwable instanceof UnknownHostException) {
            ex = new ResponseThrowable(throwable, ERROR.TIMEOUT_ERROR);
            ex.message = "主机地址未知";
            return ex;
        } else {
            ex = new ResponseThrowable(throwable, ERROR.UNKNOWN);
            ex.message = "未知错误";
            return ex;
        }

    }

    /**
     * 约定异常
     */
    public class ERROR {
        public static final int UNKNOWN = 1000;
        public static final int TIMEOUT_ERROR = 1006;
        public static final int PARSE_ERROR = 1001;
        public static final int NETWORK_ERROR = 1002;
        public static final int SSL_ERROR = 1005;
        public static final int HTTP_ERROR = 1003;
    }

    public static class ResponseThrowable extends Exception {
        public int code;
        public String message;

        public ResponseThrowable(Throwable throwable, int errorCode) {
            super(throwable);
            this.code = errorCode;
        }

        @Override
        public String getMessage() {
            return message;
        }
    }

    public static class ServerException extends RuntimeException {
        public int code;
        public String message;
    }

}
