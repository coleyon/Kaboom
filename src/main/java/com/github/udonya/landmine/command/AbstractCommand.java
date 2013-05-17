package com.github.udonya.landmine.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.github.udonya.landmine.LandMine;

/**
 * 抽象コマンド
 *
 * @author udonya
 */
public abstract class AbstractCommand extends Command {
    protected static final String clrCmd = ChatColor.AQUA.toString();			// main commands
    protected static final String clrReq = ChatColor.GREEN.toString();			// parameters
    protected static final String clrDesc = ChatColor.WHITE.toString();			// command descriptions
    protected static final String clrErr = ChatColor.RED.toString();			// errors / notices

    /**
     * オーナー情報
     */
    protected CmdOwner owner;

    protected final LandMine plugin;

    /**
     * コンストラクタ
     *
     * @param name
     */
    public AbstractCommand(String name, LandMine plugin) {
        super(name);
        this.plugin = plugin;
    }

    /**
     * 投入コマンドの構文適合チェック
     *
     * @param sender
     * @param command
     * @param s
     * @param args
     * @return
     */
    public abstract boolean areCompatibleParameters(
            CommandSender sender, Command command, String s,
            String[] args);

    @Override
    public abstract boolean execute(CommandSender sender, String commandLabel, String[] args);
}
