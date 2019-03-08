# WebSocket
Creating Endpoint with Springboot using WebSocket

1. API for sending a message
   Method : Post
   
   Endpoint : /api/message?param=<<String>>
   
2. API for collect message that has been sent out
   Method : Get
   
   Endpoint : /api/message
   
3. API for display message in real time (using WebSocket)
   Method : Get
   
   Endpoint : /api/websocket
