package com.beeline;

import com.beeline.config.CommandConfig;
import com.beeline.config.Config;
import com.beeline.configservice.ConfigurationLoader;
import com.beeline.configservice.impl.LocalConfigurationLoaderService;
import com.beeline.entity.Client;
import com.beeline.entity.MenuItem;
import com.beeline.repository.ClientRepository;
import com.beeline.repository.impl.ClientRepositoryJsonImpl;
import com.beeline.util.BalanceAmountConverter;
import com.beeline.util.ConsoleAppUtil;
import com.beeline.util.TrafficAmountConverter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Stack;

public class BeelineConsoleApp {
    private static final String INVALID_COMMAND_MESSAGE = "не верная команда меню!";
    private static final String WELCOME_MESSAGE = "Добро пожаловать в Beeline Super App! Введите имя:";
    private static final String CLIENT_NOT_FOUND_MESSAGE = "Клиент не найден! Попробуйте снова:";
    private static final String GOOD_BYE_MESSAGE = "Пока пока !";

    private final BalanceAmountConverter balanceAmountConverter = new BalanceAmountConverter();

    private final TrafficAmountConverter converter = new TrafficAmountConverter();
    private final ConsoleAppUtil appUtil = new ConsoleAppUtil();

    private final ConfigurationLoader loader = new LocalConfigurationLoaderService();
    private final ClientRepository clientRepository;

    private final Config config;

    public BeelineConsoleApp() {
        config = loader.loadConfig();
        clientRepository = new ClientRepositoryJsonImpl();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(WELCOME_MESSAGE);

        Client client = null;
        boolean clientNotFound = true;
        while (clientNotFound) {
            String name = scanner.nextLine();
            System.out.println();
            try {
                client = clientRepository.findClientByName(name);
                System.out.println(String.format(appUtil.getFirstMessage(), client.getName()));
                config.getCommandConfig().forEach(c -> System.out.print(String.format("(%s) %s; ", c.getCode(), c.getDescription())));
                System.out.println();
                clientNotFound = false;
            } catch (RuntimeException e) {
                System.out.println(CLIENT_NOT_FOUND_MESSAGE);
            }
        }

        List<MenuItem> mainMenu = config.getMenu();
        printMenu(mainMenu);
        boolean isRunning = true;
        Stack<MenuNode> session = new Stack<>();
        session.add(new MenuNode(1, mainMenu));
        while (isRunning) {
            String command = scanner.nextLine();
            if (StringUtils.equalsIgnoreCase(command, getCommand(config.getCommandConfig(), CommandConfig.CommandType.GO_TO_MAIN_MENU))) {
                session = new Stack<>();
                session.add(new MenuNode(1, mainMenu));
                printMenu(session.peek().getMenu());
                continue;
            } else if (StringUtils.equalsIgnoreCase(command, getCommand(config.getCommandConfig(), CommandConfig.CommandType.GO_BACK))) {
                if (session.size() > 1) {
                    session.pop();
                    printMenu(session.peek().getMenu());
                } else {
                    printMenu(session.peek().getMenu());
                }
                continue;
            } else if (StringUtils.equalsIgnoreCase(command, getCommand(config.getCommandConfig(), CommandConfig.CommandType.PRINT_CURRENT_MENU))) {
                printMenu(session.peek().getMenu());
                continue;
            } else if (StringUtils.equalsIgnoreCase(command, getCommand(config.getCommandConfig(), CommandConfig.CommandType.EXIT))) {
                isRunning = false;
                System.out.println(GOOD_BYE_MESSAGE);
                continue;
            }

            MenuNode current = session.peek();
            if (current.getLevel() == 1) {
                Optional<MenuItem> optionalMenu = findMenuItemByCode(mainMenu, command);
                if (optionalMenu.isPresent()) {
                    MenuItem m = optionalMenu.get();
                    if (MenuItem.SpecificMenuType.BALANCE.equals(m.getType())) {
                        System.out.println(balanceAmountConverter.getBalanceMessage(client.getBalance(), config.getBalanceConfig()));
                    } else if (MenuItem.SpecificMenuType.INTERNET_TRAFFIC.equals(m.getType())) {
                        System.out.println(converter.convertTrafficAmount(m.getMessage(), client.getTrafficAmount()));
                    }
                    if (CollectionUtils.isNotEmpty(m.getSubmenu())) {
                        session.push(new MenuNode(current.getLevel() + 1, m.getSubmenu()));
                        printMenu(m.getSubmenu());
                    }
                } else {
                    System.out.println(INVALID_COMMAND_MESSAGE);
                }
            } else {
                Optional<MenuItem> optionalMenu = findMenuItemByCode(current.getMenu(), command);
                if (optionalMenu.isPresent()) {
                    MenuItem m = optionalMenu.get();
                    System.out.println(m.getMessage());
                    if (CollectionUtils.isNotEmpty(m.getSubmenu())) {
                        session.push(new MenuNode(current.getLevel() + 1, m.getSubmenu()));
                        printMenu(m.getSubmenu());
                    }
                } else {
                    System.out.println(INVALID_COMMAND_MESSAGE);
                }
            }
        }
    }

    private String getCommand(List<CommandConfig> commandConfig, CommandConfig.CommandType commandType) {
        CommandConfig command = commandConfig.stream().filter(c -> c.getType() == commandType).findFirst().orElseThrow(() -> new RuntimeException("Invalid command " + commandType));
        return command.getCode();
    }

    private void printMenu(List<MenuItem> menu) {
        menu.stream().forEach(menuItem -> System.out.println(menuItem.getCode() + " " + menuItem.getName()));
    }

    private Optional<MenuItem> findMenuItemByCode(List<MenuItem> menu, String code) {
        return menu.stream().filter(i -> StringUtils.equals(i.getCode(), code)).findFirst();
    }

    private class MenuNode {
        private int level;
        private List<MenuItem> menu;

        public MenuNode(int level, List<MenuItem> menu) {
            this.level = level;
            this.menu = menu;
        }

        public int getLevel() {
            return level;
        }

        public List<MenuItem> getMenu() {
            return menu;
        }
    }
}
