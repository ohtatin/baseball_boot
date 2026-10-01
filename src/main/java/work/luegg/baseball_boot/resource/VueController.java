package work.luegg.baseball_boot.resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VueController {

    @GetMapping({
        "/login",
        "/stats",
        "/current-leaderboard",
        "/alltime-leaderboard"
    })
    public String vueRoutes() {
        return "forward:/index.html";
    }
}