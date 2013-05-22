package com.github.udonya.kaboom.command.kb;

import com.github.udonya.kaboom.Kaboom;
import com.github.udonya.kaboom.command.AbstractCommandHandler;

public class CommandHandler extends AbstractCommandHandler {

    public CommandHandler(Kaboom plugin) {
        cmdName = "kaboom";
        providedCmds.add(new ToggleCommand(cmdName, plugin));
        providedCmds.add(new HelpCommand(cmdName, plugin));
    }
}
