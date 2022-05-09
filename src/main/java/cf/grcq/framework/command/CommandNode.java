package cf.grcq.framework.command;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CommandNode {

    private final String name;
    private List<String> aliases;
    private String permission;
    private String description;
    private boolean hidden;
    private boolean async;

    public CommandNode() {
        this("1");
    }

    public CommandNode(String name) {
        this(name, new String[] {});
    }

    public CommandNode(String name, String[] aliases) {
        this(name, aliases, "");
    }

    public CommandNode(String name, String[] aliases, String permission) {
        this(name, aliases, permission, "");
    }

    public CommandNode(String name, String[] aliases, String permission, String description) {
        this(name, aliases, permission, description, false);
    }

    public CommandNode(String name, String[] aliases, String permission, String description, boolean hidden) {
        this(name, Lists.newArrayList(aliases), permission, description, hidden, false);
    }


}
