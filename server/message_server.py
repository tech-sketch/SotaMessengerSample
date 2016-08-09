import tornado.ioloop
import tornado.web
import tornado.websocket

cl = []
class MessageServer(tornado.websocket.WebSocketHandler):

    def check_origin(self, origin):
        return True
    
    def open(self):
        if self not in cl:
            cl.append(self)

    def on_message(self,message):
        for client in cl:
            client.write_message(message)
    
    def on_close(self):
        if self in cl:
            cl.remove(self)

application = tornado.web.Application([(r'/ws',MessageServer)])

if __name__ =="__main__":
    application.listen(8080)
    tornado.ioloop.IOLoop.current().start()
