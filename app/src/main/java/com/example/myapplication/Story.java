package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class Story {
    private Situation start_story;
    public Situation current_situation;
    private List<Situation> stories; // список историй

    //Основная история
    Story() {
        stories = new ArrayList<>();
        stories.add(createStoryOne()); // добавление первой истории
        stories.add(createStoryTwo()); // добавление второй истории
        // добавьте другие истории при необходимости
        current_situation = stories.get(0); // начальная история
    }

    private Situation createStoryTwo() {
        start_story = new Situation(
                "Новая история",
                "",
                0, 0, 0, 0);
        return start_story;
    }

    private Situation createStoryOne() {
        start_story = new Situation(
                "первая сделка (Windows)",
                "Только вы начали работать и тут же удача! Вы нашли клиента и продаете ему "
                        + "партию ПО MS Windows. Ему в принципе достаточно взять 100 коробок версии HOME.\n"
                        + "(1)у клиента денег много, а у меня нет - вы выпишете ему счет на 120 коробок ULTIMATE по 50тр\n"
                        + "(2)чуть дороже сделаем, кто там заметит - вы выпишете ему счет на 100 коробок PRO по 10тр\n"
                        + "(3)как надо так и сделаем - вы выпишете ему счет на 100 коробок HOME по 5тр ",
                3, 0, 0, 0);
        start_story.direction[0] = new Situation(
                "корпоратив",
                "Неудачное начало, ну что ж, сегодня в конторе корпоратив! "
                        + "Познакомлюсь с коллегами, людей так сказать посмотрю, себя покажу",
                1, 0, -10, -10);
        start_story.direction[0].direction[0] = new Situation(
                "",
                "",
                0, 0, 0, 0);
        start_story.direction[1] = new Situation(
                "совещание, босс доволен",
                "Сегодня будет совещание, меня неожиданно вызвали,"
                        + "есть сведения что \n босс доволен сегодняшней сделкой." +
                        "(1) Придти \n" +
                        "(2) не приходить \n",
                2, 1, 100, 0);
        start_story.direction[1].direction[0] = new Situation("Вы пришли", "Вы преуспели", 0, 10, 1000, 1000);

        start_story.direction[1].direction[1] = new Situation("Вы не пришли ", "Вы не преуспели ", 0, 10, 10, 12);

        start_story.direction[2] = new Situation(
                "мой первый лояльный клиент",
                "Мой первый клиент доволен скоростью и качеством "
                        + "моей работы. Сейчас мне звонил лично \nдиректор компании,  сообщил что скоро состоится еще более крупная сделка"
                        + " и он хотел, чтобы по ней работал именно я!\n " +
                        "(1) Согласиться \n " +
                        "(2) Отказаться\n",
                2, 0, 50, 1);
        start_story.direction[2].direction[0] = new Situation("Согласились", "Вы согласились и преуспели в этом деле ",
                0, 10, 1000, 1000);

        start_story.direction[2].direction[1] = new Situation("Отказ ", "Вы не согласились и видите как ваш коллега преуспел в этом деле ",
                0, 10, 1000, 1000);
        return start_story;
    }

    public Story loadNextStory() {
        // Получаем индекс текущей истории
        int currentIndex = stories.indexOf(current_situation) + 1;
        if (currentIndex < stories.size() - 1) {
            // Если есть следующая история в списке, загружаем ее
            current_situation = stories.get(currentIndex + 1);
            return this; // Возвращаем текущий экземпляр Story с новой историей
        } else {
            // Если это была последняя история, можете выполнить другую логику, например, начать игру заново или закончить
            return null; // Или, например, возвращать null для обозначения окончания игры
        }
    }


    public void go(int num) {
        if (num <= current_situation.direction.length)
            current_situation = current_situation.direction[num - 1];
        else
            System.out.println("Вы можете выбирать из "
                    + current_situation.direction.length + " вариантов");
    }

    public boolean isEnd() {
        return current_situation.direction.length == 0;
    }
}