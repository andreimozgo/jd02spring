package by.academy.it.command;

import by.academy.it.command.admin.AddFlightCommand;
import by.academy.it.command.admin.DeleteFlightCommand;
import by.academy.it.command.client.BuyTicketCommand;
import by.academy.it.command.client.PayTicketCommand;
import by.academy.it.command.client.RecalculateCommand;
import by.academy.it.command.user.AddRegistrationCommand;
import by.academy.it.command.user.LoginCommand;
import by.academy.it.command.user.LogoutCommand;
import by.academy.it.command.user.RegistrationCommand;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    ADDFLIGHT {
        {
            this.command = new AddFlightCommand();
        }
    },
    REGISTRATION {
        {
            this.command = new RegistrationCommand();
        }
    },
    ADDREGISTRATION {
        {
            this.command = new AddRegistrationCommand();
        }
    },
    DELETEFLIGHT {
        {
            this.command = new DeleteFlightCommand();
        }
    },
    RECALCULATE {
        {
            this.command = new RecalculateCommand();
        }
    },
    BUYTICKET {
        {
            this.command = new BuyTicketCommand();
        }
    },
    PAYTICKET {
        {
            this.command = new PayTicketCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}

