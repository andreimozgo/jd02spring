package by.academy.it.services;

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

