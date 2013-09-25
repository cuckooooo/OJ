package huawei;

import java.util.HashSet;
import java.util.Set;

/**
 * <pre>
 * 蜂窝中的一个点
 * 
 * </pre>
 */
public class Position
{
    /**
     * 圈号
     */
    private int cycleNo;
    
    /**
     * 圈中的编号
     */
    private int columnNo;
    
    /**
     * 真实编号
     */
    private int realNum;
    
    public Position(int realNum)
    {
        this.realNum = realNum;
        transfer();
    }
    
    /**
     * <pre>
     * 获取其所有的6个相邻点
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
        
        // 第一圈没有内圈,只有外圈相邻点
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
        
        // 当前点的列号低于圈号减1时，它与2个相邻的内圈点相邻
        if (getColumnNo() < innerCycleNo)
        {
            // 表示取最后一列(列号最大的那个点)
            if (getColumnNo() == 1)
            {
                innerPosition = getMaxCyclePosition(innerCycleNo);
            }
            // 否则内圈列号取getColumnNo()-1
            else
            {
                innerRealNum = HoneycombUtils.getMinOfRealNumInCycle(innerCycleNo) - 1 + getColumnNo() - 1;
                innerPosition = new Position(innerRealNum);
            }
            closestPositions.add(innerPosition);
            
            // 添加另一个内圈相邻点
            innerRealNum = HoneycombUtils.getMinOfRealNumInCycle(innerCycleNo) - 1 + getColumnNo();
            innerPosition = new Position(innerRealNum);
            closestPositions.add(innerPosition);
            
            // 添加2个外圈相邻点
            outerRealNum = HoneycombUtils.getMinOfRealNumInCycle(outerCycleNo) - 1 + getColumnNo();
            outerPosition = new Position(outerRealNum);
            closestPositions.add(outerPosition);
            
            closestPositions.add(outerPosition.getNextPosition());
        }
        // 内环只有一个相邻点
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
            
            // 添加3个外圈相邻的点
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
        // 否则内环有2个相邻点
        else
        {
            // 找出同一圈中列号较小且只有一个相邻点的点
            int addColumnNo = getColumnNo() % innerCycleNo;
            int criticalColumnNo = getColumnNo() - addColumnNo;
            
            innerRealNum =
                HoneycombUtils.getMinOfRealNumInCycle(innerCycleNo) - 1 + criticalColumnNo * (innerCycleNo - 1)
                    / innerCycleNo + addColumnNo - 1;
            
            innerPosition = new Position(innerRealNum);
            closestPositions.add(innerPosition);
            
            closestPositions.add(innerPosition.getNextPosition());
            
            // 添加另一个外圈点
            outerRealNum =
                HoneycombUtils.getMinOfRealNumInCycle(outerCycleNo) - 1 + innerCycleNo
                    + (criticalColumnNo / innerCycleNo - 1) * getCycleNo() + 2 + addColumnNo - 1;
            
            outerPosition = new Position(outerRealNum);
            closestPositions.add(outerPosition);
            
            closestPositions.add(outerPosition.getNextPosition());
        }
        
        // 添加本圈中的2个与之相邻的点
        closestPositions.add(getLastPosition());
        closestPositions.add(getNextPosition());
        
        return closestPositions;
    }
    
    /**
     * 重载方法
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
     * 重载方法
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
     * 重载方法
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
     * 把蜂窝上一个点的编号转换成圈号 + 圈内的编号
     * </pre>
     */
    private void transfer()
    {
        // 在第一圈
        if (getRealNum() == 1)
        {
            setCycleNo(1);
            setColumnNo(1);
        }
        // 在其它圈
        else
        {
            int cycle = 2;
            while (HoneycombUtils.getMaxOfRealNumInCycle(cycle) < getRealNum())
            {
                cycle++;
            }
            setCycleNo(cycle);
            
            // 计算真实编号对应圈中的编号
            int minCycleNum = HoneycombUtils.getMinOfRealNumInCycle(cycle);
            int columnNo = getRealNum() - minCycleNum + 1;
            setColumnNo(columnNo);
        }
    }
    
    /**
     * <pre>
     * 获取一圈中的下一个位置，如果是最后一个位置，下一个指向第一个；否则列号加1
     * 
     * @return
     * </pre>
     */
    private Position getNextPosition()
    {
        // 最后一个的下一个指向圈中的第一个
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
     * 获取同一圈中列号小1的点
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
     * 获取指定圈的最大列号对应的点
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
     * 获取当前圈的最大点
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
     * 获取指定圈中最小列号对应的最小点
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
     * 获取当前圈的最小点
     * 
     * @return
     * </pre>
     */
    private Position getMinCyclePosition()
    {
        return getMinCyclePosition(getCycleNo());
    }
    
    /**
     * 获取 cycleNo
     * 
     * @return 返回 cycleNo
     */
    private int getCycleNo()
    {
        return cycleNo;
    }
    
    /**
     * 获取 columnNo
     * 
     * @return 返回 columnNo
     */
    private int getColumnNo()
    {
        return columnNo;
    }
    
    /**
     * 获取 realNum
     * 
     * @return 返回 realNum
     */
    private int getRealNum()
    {
        return realNum;
    }
    
    /**
     * 设置 cycleNo
     * 
     * @param 对cycleNo进行赋值
     */
    private void setCycleNo(int cycleNo)
    {
        this.cycleNo = cycleNo;
    }
    
    /**
     * 设置 columnNo
     * 
     * @param 对columnNo进行赋值
     */
    private void setColumnNo(int columnNo)
    {
        this.columnNo = columnNo;
    }
}