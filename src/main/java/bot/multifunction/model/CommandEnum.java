package bot.multifunction.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
@Getter
public enum CommandEnum {

//"/add_reminder\n\n/edit_reminder" +
//        "\n\n/delete_reminder\n\n/vocabulary\n\n/languages"));
    START("/start"),

    HELP("/help"),

    ADD_REMAINDER("/add_reminder"),

    EDIT_REMAINDER("/edit_reminder"),

    DELETE_REMAINDER("/delete_reminder"),

    VOCAB("/vocabulary"),

    LANGUAGES("/languages"),

    GET_RANDOM_WORD("/get_random_word");

    private final String value;

    CommandEnum(String value) {
        this.value = value;
    }

    private static final Map<String, CommandEnum> MAP = Arrays.<CommandEnum>stream(CommandEnum.values())
            .collect(Collectors.toMap(CommandEnum::getValue, commandEnum -> commandEnum));

    public static CommandEnum of(String command) {
        final CommandEnum commandEnum = MAP.get(command);
        if (commandEnum == null) {
            throw new IllegalArgumentException("Unsupported command.");
        }
        return commandEnum;
    }
}

