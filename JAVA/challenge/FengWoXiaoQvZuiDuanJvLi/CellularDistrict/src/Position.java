package huawei;

import java.util.HashSet;
import java.util.Set;

/**
 * <pre>
 * �����е�һ����
 * 
 * </pre>
 */
public class Position
{
    /**
     * Ȧ��
     */
    private int cycleNo;
    
    /**
     * Ȧ�еı��
     */
    private int columnNo;
    
    /**
     * ��ʵ���
     */
    private int realNum;
    
    public Position(int realNum)
    {
        this.realNum = realNum;
        transfer();
    }
    
    /**
     * <pre>
     * ��ȡ�����е�6�����ڵ�
     * 
     * @return
     * </pre>
     */
    public Set<Position> getClosestPositions()
    {
        Set<Position> closestPositions = new HashSet<Position>();
        Position nextPosition = null;
        Position outerPosition = null;
        
        int outerCycleNo = getCycleNo() + 1;
        int outerRealNum = 0;
        
        // ��һȦû����Ȧ,ֻ����Ȧ���ڵ�
        if (getCycleNo() == 1)
        {
            for (int i = 2; i <= 7; i++)
            {
                nextPosition = new Position(i);
                closestPositions.add(nextPosition);
            }
            
            return closestPositions;
        }
        
        Position innerPosition = null;
        int innerRealNum = 0;
        int innerCycleNo = getCycleNo() - 1;
        
        // ��ǰ����кŵ���Ȧ�ż�1ʱ������2�����ڵ���Ȧ������
        if (getColumnNo() < innerCycleNo)
        {
            // ��ʾȡ���һ��(�к������Ǹ���)
            if (getColumnNo() == 1)
            {
                innerPosition = getMaxCyclePosition(innerCycleNo);
            }
            // ������Ȧ�к�ȡgetColumnNo()-1
            else
            {
                innerRealNum = HoneycombUtils.getMinOfRealNumInCycle(innerCycleNo) - 1 + getColumnNo() - 1;
                innerPosition = new Position(innerRealNum);
            }
            closestPositions.add(innerPosition);
            
            // �����һ����Ȧ���ڵ�
            innerRealNum = HoneycombUtils.getMinOfRealNumInCycle(innerCycleNo) - 1 + getColumnNo();
            innerPosition = new Position(innerRealNum);
            closestPositions.add(innerPosition);
            
            // ���2����Ȧ���ڵ�
            outerRealNum = HoneycombUtils.getMinOfRealNumInCycle(outerCycleNo) - 1 + getColumnNo();
            outerPosition = new Position(outerRealNum);
            closestPositions.add(outerPosition);
            
            closestPositions.add(outerPosition.getNextPosition());
        }
        // �ڻ�ֻ��һ�����ڵ�
        else if (getColumnNo() % innerCycleNo == 0)
        {
            if (innerCycleNo == 1)
            {
                innerPosition = new Position(1);
            }
            else
            {
                innerRealNum =
                    HoneycombUtils.getMinOfRealNumInCycle(innerCycleNo) - 1 + getColumnNo() * (innerCycleNo - 1)
                        / innerCycleNo;
                innerPosition = new Position(innerRealNum);
            }
            closestPositions.add(innerPosition);
            
            // ���3����Ȧ���ڵĵ�
            outerRealNum =
                HoneycombUtils.getMinOfRealNumInCycle(outerCycleNo) - 1 + innerCycleNo
                    + (getColumnNo() / innerCycleNo - 1) * getCycleNo();
            
            outerPosition = new Position(outerRealNum);
            closestPositions.add(outerPosition);
            
            nextPosition = outerPosition.getNextPosition();
            closestPositions.add(nextPosition);
            
            nextPosition = nextPosition.getNextPosition();
            closestPositions.add(nextPosition);
            
        }
        // �����ڻ���2�����ڵ�
        else
        {
            // �ҳ�ͬһȦ���кŽ�С��ֻ��һ�����ڵ�ĵ�
            int addColumnNo = getColumnNo() % innerCycleNo;
            int criticalColumnNo = getColumnNo() - addColumnNo;
            
            innerRealNum =
                HoneycombUtils.getMinOfRealNumInCycle(innerCycleNo) - 1 + criticalColumnNo * (innerCycleNo - 1)
                    / innerCycleNo + addColumnNo - 1;
            
            innerPosition = new Position(innerRealNum);
            closestPositions.add(innerPosition);
            
            closestPositions.add(innerPosition.getNextPosition());
            
            // �����һ����Ȧ��
            outerRealNum =
                HoneycombUtils.getMinOfRealNumInCycle(outerCycleNo) - 1 + innerCycleNo
                    + (criticalColumnNo / innerCycleNo - 1) * getCycleNo() + 2 + addColumnNo - 1;
            
            outerPosition = new Position(outerRealNum);
            closestPositions.add(outerPosition);
            
            closestPositions.add(outerPosition.getNextPosition());
        }
        
        // ��ӱ�Ȧ�е�2����֮���ڵĵ�
        closestPositions.add(getLastPosition());
        closestPositions.add(getNextPosition());
        
        return closestPositions;
    }
    
    /**
     * ���ط���
     * 
     * @return
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + realNum;
        return result;
    }
    
    /**
     * ���ط���
     * 
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Position other = (Position)obj;
        if (realNum != other.realNum)
            return false;
        return true;
    }
    
    /**
     * ���ط���
     * 
     * @return
     */
    @Override
    public String toString()
    {
        return "Position [realNum=" + realNum + "]";
    }
    
    /**
     * <pre>
     * �ѷ�����һ����ı��ת����Ȧ�� + Ȧ�ڵı��
     * </pre>
     */
    private void transfer()
    {
        // �ڵ�һȦ
        if (getRealNum() == 1)
        {
            setCycleNo(1);
            setColumnNo(1);
        }
        // ������Ȧ
        else
        {
            int cycle = 2;
            while (HoneycombUtils.getMaxOfRealNumInCycle(cycle) < getRealNum())
            {
                cycle++;
            }
            setCycleNo(cycle);
            
            // ������ʵ��Ŷ�ӦȦ�еı��
            int minCycleNum = HoneycombUtils.getMinOfRealNumInCycle(cycle);
            int columnNo = getRealNum() - minCycleNum + 1;
            setColumnNo(columnNo);
        }
    }
    
    /**
     * <pre>
     * ��ȡһȦ�е���һ��λ�ã���������һ��λ�ã���һ��ָ���һ���������кż�1
     * 
     * @return
     * </pre>
     */
    private Position getNextPosition()
    {
        // ���һ������һ��ָ��Ȧ�еĵ�һ��
        if (getRealNum() == HoneycombUtils.getMaxOfRealNumInCycle(getCycleNo()))
        {
            return getMinCyclePosition();
        }
        else
        {
            return new Position(getRealNum() + 1);
        }
    }
    
    /**
     * <pre>
     * ��ȡͬһȦ���к�С1�ĵ�
     * 
     * @return
     * </pre>
     */
    private Position getLastPosition()
    {
        if (getRealNum() == HoneycombUtils.getMinOfRealNumInCycle(getCycleNo()))
        {
            return getMaxCyclePosition();
        }
        else
        {
            return new Position(getRealNum() - 1);
        }
    }
    
    /**
     * <pre>
     * ��ȡָ��Ȧ������кŶ�Ӧ�ĵ�
     * 
     * @param cycleNo
     * @return
     * </pre>
     */
    private Position getMaxCyclePosition(int cycleNo)
    {
        return new Position(HoneycombUtils.getMaxOfRealNumInCycle(cycleNo));
    }
    
    /**
     * <pre>
     * ��ȡ��ǰȦ������
     * 
     * @return
     * </pre>
     */
    private Position getMaxCyclePosition()
    {
        return getMaxCyclePosition(getCycleNo());
    }
    
    /**
     * <pre>
     * ��ȡָ��Ȧ����С�кŶ�Ӧ����С��
     * 
     * @return
     * </pre>
     */
    private Position getMinCyclePosition(int cycleNo)
    {
        return new Position(HoneycombUtils.getMinOfRealNumInCycle(cycleNo));
    }
    
    /**
     * <pre>
     * ��ȡ��ǰȦ����С��
     * 
     * @return
     * </pre>
     */
    private Position getMinCyclePosition()
    {
        return getMinCyclePosition(getCycleNo());
    }
    
    /**
     * ��ȡ cycleNo
     * 
     * @return ���� cycleNo
     */
    private int getCycleNo()
    {
        return cycleNo;
    }
    
    /**
     * ��ȡ columnNo
     * 
     * @return ���� columnNo
     */
    private int getColumnNo()
    {
        return columnNo;
    }
    
    /**
     * ��ȡ realNum
     * 
     * @return ���� realNum
     */
    private int getRealNum()
    {
        return realNum;
    }
    
    /**
     * ���� cycleNo
     * 
     * @param ��cycleNo���и�ֵ
     */
    private void setCycleNo(int cycleNo)
    {
        this.cycleNo = cycleNo;
    }
    
    /**
     * ���� columnNo
     * 
     * @param ��columnNo���и�ֵ
     */
    private void setColumnNo(int columnNo)
    {
        this.columnNo = columnNo;
    }
}