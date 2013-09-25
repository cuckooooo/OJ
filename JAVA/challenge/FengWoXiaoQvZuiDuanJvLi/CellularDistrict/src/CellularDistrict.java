package huawei;

import java.util.HashSet;
import java.util.Set;

/**
 * 蜂窝小区，以1为中心，顺时针编号，编号最大限定为100000。 求任意两编号之间的最短距离。 两个相邻小区的距离为1
 * 
 * 参见试题说明的示意图
 * 
 */
public class CellularDistrict
{
	public static final int SUCC = 0;
	public static final int FAIL = -1;
	public static final int MIN_NUM = 1;
	public static final int MAX_NUM = 100000;
	public static int iResult= 0;
	public static int tempMAX = 0;

    private static Set<Position> positions = new HashSet<Position>();  
    
    private static Set<Position> closestPositions = new HashSet<Position>();  
      
    public CellularDistrict()  
    {  
    }  
	
	/**
	 * 小区编号有效性判断函数
	 * 
	 * @param num 小区编号
	 * @return 有效返回ture，无效返回false
	 */
	private boolean isInputParametersLegal(int num)
	{
		if (num < MIN_NUM || num > MAX_NUM)
		{
			return false;
		}		
		return true;
	}
	
	/**
	 * 初始化蜂窝小区信息
	 * 
	 * @param iMaxSeqValue
	 *            蜂窝小区的最大值编号，注：编号从1开始
	 * @return 成功返回0，失败返回-1
	 */
	public int initCellularDistrict(int iMaxSeqValue)
	{
		if (!isInputParametersLegal(iMaxSeqValue))
		{
			return FAIL;
		}
		
		positions.clear();  
		iResult = 0;  
		closestPositions.clear(); 
		tempMAX = iMaxSeqValue;
		
		return SUCC;
	}

	/**
	 * 计算出蜂窝小区指定两点（编号值）之间的最短距离
	 * 
	 * @param iFirstValue
	 *            起点编号值
	 * @param iSecondValue
	 *            终点编号值
	 * @return 计算成功返回最短距离，失败返回-1
	 */
	public int getShortestPathLength(int iFirstValue, int iSecondValue)
	{
		if (!isInputParametersLegal(iFirstValue) || !isInputParametersLegal(iSecondValue))
		{
			return FAIL;
		}
		if (iFirstValue > tempMAX || iSecondValue > tempMAX)
		{
			return FAIL;
		}

		if (iFirstValue == iSecondValue)
		{
			return 0;
		}
		

		Position startPosition = new Position(Math.min(iFirstValue, iSecondValue));  
		Position endPosition = new Position(Math.max(iFirstValue, iSecondValue)); 
		int count = getShortestPathLength(startPosition, endPosition);
		return count; 
	}
	

	private static int getShortestPathLength(Position startPosition, Position endPosition)  
	{  
		closestPositions.add(startPosition);  
		searchNext(endPosition);  
		return iResult;  
	}  

	/**
     * <pre>
     * 广度优先遍历,每次取当前点的所有相邻点跟目标点比较，相等就退出，否则一直迭代查找
     * 
     * @param endPosition
     * </pre>
     */
    private static void searchNext(Position endPosition)
    {
        if (closestPositions.contains(endPosition))
        {
            return;
        }
        
        Set<Position> copyPositions = new HashSet<Position>(closestPositions.size());
        copyPositions.addAll(closestPositions);
        
        closestPositions.clear();
        
        for (Position closestPosition : copyPositions)
        {
            addClosestPositions(closestPosition.getClosestPositions());
        }
        
        iResult++;
        searchNext(endPosition);
    }
    
    private static void addClosestPositions(Set<Position> oneOfpositions)
    {
        for (Position position : oneOfpositions)
        {
            if (!positions.contains(position))
            {
                closestPositions.add(position);
            }
            
            positions.add(position);
        }
    }


	/**
	 * 清空相关信息
	 */
	public void clear()
	{
		tempMAX = 0;
	}
}
