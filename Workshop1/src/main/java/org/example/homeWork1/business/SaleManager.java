package org.example.homeWork1.business;

import org.example.homeWork1.entities.AgeLimitGame;
import org.example.homeWork1.entities.Campaign;
import org.example.homeWork1.entities.Gamer;
import org.example.homeWork1.entities.Game;
import org.example.homeWork1.entities.Sale;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SaleManager
{
    private List<Sale> sales;

    public SaleManager(List<Sale> sales)
    {
        this.sales = sales;
    }

    public void saleService(Gamer gamer, Game game, Campaign campaign)
    {
        double currentGamePrice = game.getPrice() - ((campaign.getDiscountRate() / 100) * game.getPrice());
        int daysNum = (int) TimeUnit.DAYS.convert((new Date().getTime() - gamer.getBirthDay().getTime()), TimeUnit.MILLISECONDS);

        if ( ((game.getClass() == Game.class) && (gamer.getBalance() >= currentGamePrice)) || ( (game.getClass() == AgeLimitGame.class) && (gamer.getBalance() >= currentGamePrice) && (daysNum >= (18*365))) )
        {
            gamer.setBalance(gamer.getBalance() - currentGamePrice);
            gamer.addGames(game);
            sales.add(new Sale(1, game, gamer, campaign, new Date(), currentGamePrice));
            System.out.println(gamer.getFirstName() + ", " + game.getName() + " oyununu aldınız.");

        }
        else
        {
            System.out.println(gamer.getFirstName() + ", " + "satın alma işleminiz gerçekleşmedi.");
        }
    }

    public List<Sale> getSales()
    {
        return sales;
    }
}