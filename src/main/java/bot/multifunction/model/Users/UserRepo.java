package bot.multifunction.model.Users;

import bot.multifunction.model.DateManager;
import bot.multifunction.model.TimeManager;
import bot.multifunction.model.steps.Steps;
import bot.multifunction.model.vocabluary.VocabSwitchInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepo {
    public static Map<Long, VocabSwitchInfo> VOCAB_SWITCH_INFO = new HashMap<>();
    public static Map<Long, Steps> USER_STEP = new HashMap<>();
    public static Map<Long, TimeManager> TIME = new HashMap<>();
    public static Map<Long, DateManager> DATE = new HashMap<>();
    public static Map<Long, String> DATE_COLLECTOR = new HashMap<>();
    public static Map<Long, Integer> MESSAGE_TO_DELETE = new HashMap<>();
}
