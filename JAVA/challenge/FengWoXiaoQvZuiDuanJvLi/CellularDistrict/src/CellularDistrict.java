package huawei;

import java.util.HashSet;
import java.util.Set;

/**
 * ����С������1Ϊ���ģ�˳ʱ���ţ��������޶�Ϊ100000�� �����������֮�����̾��롣 ��������С���ľ���Ϊ1
 * 
 * �μ�����˵����ʾ��ͼ
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
	 * С�������Ч���жϺ���
	 * 
	 * @param num С�����
	 * @return ��Ч����ture����Ч����false
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
	 * ��ʼ������С����Ϣ
	 * 
	 * @param iMaxSeqValue
	 *            ����С�������ֵ��ţ�ע����Ŵ�1��ʼ
	 * @return �ɹ�����0��ʧ�ܷ���-1
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
	 * ���������С��ָ�����㣨���ֵ��֮�����̾���
	 * 
	 * @param iFirstValue
	 *            �����ֵ
	 * @param iSecondValue
	 *            �յ���ֵ
	 * @return ����ɹ�������̾��룬ʧ�ܷ���-1
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
     * ������ȱ���,ÿ��ȡ��ǰ����������ڵ��Ŀ���Ƚϣ���Ⱦ��˳�������һֱ��������
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
	 * ��������Ϣ
	 */
	public void clear()
	{
		tempMAX = 0;
	}
}
