package command;

import request.RRHandler;
import utility.ObjectForServer;
import utility.Reader;

import java.io.IOException;

public class Login extends CommandAbstract {

    Reader reader;

    public Login(String name, String description, boolean isArgument, RRHandler rrHandler, Reader reader) {
        super(name, description, isArgument, rrHandler);
        this.reader = reader;
    }

    public boolean executeR(String arg) {
        try {
            rrHandler.req(this.getName(), arg);
            ObjectForServer response = rrHandler.res();
            if (response.isAnswerB()) {
                System.out.println("Welcome!");
                return true;
            } else {
                System.out.println("The username or password was entered incorrectly");
                return false;
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
