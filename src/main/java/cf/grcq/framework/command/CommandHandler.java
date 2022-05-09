package cf.grcq.framework.command;

import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandHandler {

    private static Map<JavaPlugin, List<CommandNode>> commands = new HashMap<>();
    private final JavaPlugin owner;
    private CommandMap map;

    public CommandHandler(JavaPlugin owner) {
        this.owner = owner;

        refreshMap();
    }

    @SneakyThrows
    private void refreshMap() {
        SimplePluginManager manager = (SimplePluginManager) Bukkit.getServer().getPluginManager();

        Field field = SimplePluginManager.class.getDeclaredField("commandMap");
        field.setAccessible(true);

        map = (CommandMap) field.get(manager);
    }

    public void register(Class<?> clazz) {

        for (Method method : clazz.getMethods()) {
            if (method.isAnnotationPresent(Command.class)) {
                Command info = method.getDeclaredAnnotation(Command.class);
                CommandNode node = new CommandNode(info.name(), Lists.newArrayList(info.aliases()), info.permission(), info.description(), info.hidden(), info.async());

                BukkitCommand command = new BukkitCommand(node);

                map.register(owner.getDescription().getName().toLowerCase(), command);
                commands.putIfAbsent(owner, new ArrayList<>());
                commands.get(owner).add(node);
            }
        }
    }

}
