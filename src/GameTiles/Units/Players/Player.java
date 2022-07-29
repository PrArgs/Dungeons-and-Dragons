package GameTiles.Units.Players;

import GameTiles.DesignPatterns.Visitor;
import Dungeons_and_Dragons.GameBoard;
import GameTiles.Wall;
import UI.MessageCallback;
import GameTiles.Units.Ability;
import GameTiles.Units.Enemies.Enemy;
import GameTiles.Units.Unit;

import java.util.ArrayList;
import java.util.Random;

public abstract class Player extends Unit {


    ArrayList<Enemy> enemies;
    protected int experience = 0;
    protected int level = 1;
    protected boolean isAlive;
    protected Ability specialAbility;

    protected static final int PLAYER_EXP_MULTIPLAYER =10;
    protected static final int PLAYER_HEALTH_MULTIPLAYER =10;
    protected static final int PLAYER_ATTACK_MULTIPLAYER =4;
    protected static final int PLAYER_DEFENSE_MULTIPLAYER =4;

    protected Player(String name, int attack, int defense) {
        super(PLAYERSIGN, name, attack, defense);
        isAlive=true;
    }


    public void levelUp(){
        experience = experience-(PLAYER_EXP_MULTIPLAYER*level);
         level = level + 1;
         health.setHealthPool(PLAYER_HEALTH_MULTIPLAYER*level);
         health.setHealthAmount(PLAYER_HEALTH_MULTIPLAYER*level);
         attack =  (PLAYER_ATTACK_MULTIPLAYER*level);
         defense = (PLAYER_DEFENSE_MULTIPLAYER*level);
    }

    public void OnAbilityCast() throws Exception {
        throw new Exception("Not implemented");
    }



    @Override
    protected void battle(Unit defender){
        super.battle(defender);
        /*if(defender.getHealth().getHealthPool()<=0)
        {
            MessageCallback.print( "you killed "+ defender.getName());
            this.addExprincePoints(this.experience);
            this.initialize(defender.getPosition());
            GameBoard.reomve(defender);
            enemies.remove(defender);
        }
        defender.*/
    }

    public void battle (Enemy enemy, int attack)
    {
            int defense= new Random().nextInt(enemy.getAttack());
            if(attack -defense > 0)
            {
                enemy.damage(attack - defense);
            }

    }
    private void addExprincePoints(int experience) {
        this.experience=this.experience+experience;

    }

    public void death ()
    {
        this.isAlive=false;
        MessageCallback.print("Game Over you died");
    }

    @Override
    public void accept(Visitor v)
    {
        v.visit(this);
    }

    @Override
    public void visit(Player p) {

    }

    @Override
    public void visit (Enemy e)
    {
        this.battle(e);
    }



}
