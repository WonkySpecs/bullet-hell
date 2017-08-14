/**
* EnemyStraightPathFollower defines anj EnemyShip sub class which
* contains an ArrayList of points which it moves between in order.
* Individual speeds for each point can be specified at construction
* in an ArrayList<Double> or a single speed value can be specified
* which will then be used for all movement.
*
* @author  Will Taylor
* @version 0.1
* @since   13-08-2017
*/

package src.gameobjects;

import java.util.HashMap;
import java.util.ArrayList;
import java.awt.geom.*;

import src.Hitbox;
import src.HitboxCircle;
import src.animation.*;
import src.ProjectileData;

public class EnemyStraightPathFollower extends EnemyStraightFloater{
	private int curTargetPoint;
	private ArrayList<Point2D.Double> pointList;
	private ArrayList<Double> speedList;

	public EnemyStraightPathFollower(int hp,
										ArrayList<Point2D.Double> pointList, ArrayList<Double> speedList,
										int score, 
										Hitbox hitbox,
										HashMap<String, Animation> animations,
										ProjectileData projType, ItemDrop itemDrop){
		super(pointList.get(0).getX(), pointList.get(0).getY(), hp,
				calcXvel(pointList.get(0), new Point2D.Double(pointList.get(0).getX(), pointList.get(0).getY()), speedList.get(0)),
				calcYvel(pointList.get(0), new Point2D.Double(pointList.get(0).getX(), pointList.get(0).getY()), speedList.get(0)),
				score, hitbox, animations, projType, itemDrop);
		curTargetPoint = 0;
		this.pointList = pointList;
		this.speedList = speedList;
	}

	//Convenience constructor for enemy that always moves at same speed
	public EnemyStraightPathFollower(int hp,
										ArrayList<Point2D.Double> pointList, double speed,
										int score, 
										Hitbox hitbox,
										HashMap<String, Animation> animations,
										ProjectileData projType, ItemDrop itemDrop){
		this(hp,
				pointList, buildSpeedList(speed, pointList.size()),
				score, 
				hitbox,
				animations,
				projType, itemDrop);
	}

	@Override
	public void update(int screenWidth, int screenHeight){
		super.update(screenWidth, screenHeight);
		
		//Once we reach a point, head towards next
		if(getPos().distance(pointList.get(curTargetPoint)) < 1){
			curTargetPoint += 1;
		}

		setXvel(calcXvel(pointList.get(curTargetPoint), getPos(), speedList.get(curTargetPoint)));
		setYvel(calcYvel(pointList.get(curTargetPoint), getPos(), speedList.get(curTargetPoint)));
	}

	public static double calcXvel(Point2D.Double target, Point2D.Double pos, double speed){
		if(speed >= target.distance(pos.getX(), pos.getY())){
			return target.getX() - pos.getX();
		}
		else{
			double angle = Math.atan2(target.getY() - pos.getY(), target.getX() - pos.getX());
			return speed * Math.cos(angle);			
		}
	}

	public static double calcYvel(Point2D.Double target, Point2D.Double pos, double speed){
		if(speed >= target.distance(pos.getX(), pos.getY())){
			return target.getY() - pos.getY();
		}
		else{
			double angle = Math.atan2(target.getY() - pos.getY(), target.getX() - pos.getX());
			return speed * Math.sin(angle);			
		}
	}

	private static ArrayList<Double> buildSpeedList(double speed, int size){
		ArrayList<Double> speedList = new ArrayList<>();
		for(int i = 0; i < size; i++){
			speedList.add(speed);
		}
		return speedList;
	}
}