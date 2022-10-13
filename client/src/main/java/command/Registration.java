package command;

import request.RRHandler;
import utility.ObjectForServer;
import utility.Reader;

import java.io.IOException;

public class Registration extends CommandAbstract {

    Reader reader;

    public Registration(String name, String description, boolean isArgument, RRHandler rrHandler, Reader reader) {
        super(name, description, isArgument, rrHandler);
        this.reader = reader;
    }

    public boolean executeR(String arg) {
        try {
            rrHandler.req(this.getName(), arg);
            ObjectForServer response = rrHandler.res();
            if (response.isAnswerB()) {
                System.out.println("Thanks for registration!");
                return true;
            } else {
                System.out.println("A user with this name already exists");
                return false;
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}