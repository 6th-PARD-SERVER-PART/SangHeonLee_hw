package pard.server.com.hw1;

import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/menu")
public class myController {

    public Map<String, Integer> menu = new HashMap<>();

    @GetMapping
    public Map<String, Integer> allMenu() {
        return menu;
    }

    @RequestMapping("/{menu_name}/{price}")
    public Map<String, Integer> addMenu(@PathVariable String menu_name, @PathVariable int price) {
        menu.put(menu_name, price);
        return menu;
    }

    @GetMapping(params = "menu_name")
    public int howMuch(@RequestParam("menu_name") String menu_name) {
        if (menu.containsKey(menu_name)) {
            return menu.get(menu_name);
        } else {
            return -1;
        }
    }

    @PatchMapping("/{menu_name}/{price}")
    public Map<String, Integer> updateMenu(@PathVariable String menu_name, @PathVariable int price) {
        if (menu.containsKey(menu_name)) {
            menu.put(menu_name, price);
        }
        return menu;
    }

    @DeleteMapping("/{menu_name}")
    public Map<String, Integer> deleteMenu(@PathVariable String menu_name) {
        if (menu.containsKey(menu_name)) {
            menu.remove(menu_name);
            return menu;
        } else {
            return null;
        }
    }
}

