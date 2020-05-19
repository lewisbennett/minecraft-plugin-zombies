package com.mango.zombies.services.base;

import org.bukkit.command.CommandSender;

public interface MessagingService {

    //region Public Methods
    /**
     * Shows a formatted error message to the sender.
     */
    void error(CommandSender sender, String error);

    /**
     * Shows a formatted success message to the sender.
     */
    void success(CommandSender sender, String message);
    //endregion
}
