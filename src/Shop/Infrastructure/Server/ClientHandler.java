package Shop.Infrastructure.Server;

import Shop.BLL.Handler;
import Shop.Infrastructure.Models.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;

public class ClientHandler extends Thread
{
    private final DataInputStream input;
    private final DataOutputStream output;
    private final Socket client;
    private final Handler requestHandler;

    ClientHandler(Socket s, Connection db) throws IOException, SQLException
    {
        client = s;
        input = new DataInputStream(s.getInputStream());
        output = new DataOutputStream(s.getOutputStream());
        requestHandler = new Handler(db);
    }

    @Override
    public void run()
    {
        String request;
        String response = "";
        ObjectMapper mapper = new ObjectMapper();
        while (true)
        {
            try
            {
                request = input.readUTF();
                Message message = mapper.readValue(request, Message.class);
                if(message.getHead().equals("EXIT"))
                {
                    System.out.println("Клиент " + client.getInetAddress() + ":" + client.getPort() + " вышел.");
                    client.close();
                    return;
                }
                response = mapper.writeValueAsString(requestHandler.Handle(message));
            }
            catch (IOException | SQLException | ClassCastException e)
            {
                e.printStackTrace();
                try
                {
                    response = mapper.writeValueAsString(new Message<>("ERROR", String.class, "Ошибка на стороне сервера"));
                } catch (JsonProcessingException ex)
                {
                    ex.printStackTrace();
                }
            }
            finally
            {
                try
                {
                    if(!client.isClosed())
                        output.writeUTF(response);
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
