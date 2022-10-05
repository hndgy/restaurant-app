package fr.hndgy.restaurantapp.adapter.in;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

public class CustomInterceptor implements HandshakeInterceptor {

   

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, @Nullable Exception e) {
        if(serverHttpRequest.getHeaders() == null )
            return ;
        if(serverHttpRequest.getHeaders().get("Sec-WebSocket-Protocol") == null)
            return ;
        String protocol = (String) serverHttpRequest.getHeaders().get("Sec-WebSocket-Protocol").get(0);
        if(protocol == null)
            return ;

        serverHttpResponse.getHeaders().add("Sec-WebSocket-Protocol", protocol);

    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
            Map<String, Object> attributes) throws Exception {
        return true;
    }

}
