package bot.multifunction.model.Users;

import bot.multifunction.model.TimeManager;
import bot.multifunction.model.steps.Steps;
import bot.multifunction.model.vocabluary.VocabSwitchInfo;

import java.util.HashMap;
import java.util.Map;

public class UserRepo {
    public static Map<Long, VocabSwitchInfo> VOCAB_SWITCH_INFO = new HashMap<>();
    public static Map<Long, Steps> USER_STEP = new HashMap<>();
    public static Map<Long, TimeManager> REMINDER_TIME = new HashMap<Long, bot.multifunction.model.TimeManager>();
}
