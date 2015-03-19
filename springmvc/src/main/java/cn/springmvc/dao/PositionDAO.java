package cn.springmvc.dao;

import cn.springmvc.model.Position;

import java.util.List;


public interface PositionDAO {

	/**
	 * 添加新用户
	 * @param Position
	 * @return
	 */
	public Position insertPosition(Position Position);

    /**
     * 删除Position
     * @param Position
     */
    public void deletePosition(Position Position);

    /**
     * 改Position
     * @param Position
     * @return
     */
    public Position updatePosition(Position Position);

    /**
     * 查询所有Position
     * @return 所有Position
     */
    public List<Position> selectAllPositions();

    /**
     * 条件查询Position
     * @return Position
     */
    public List<Position> selectPositionsByCon(Position Position);

    /**
     * Id查询Position
     * @return Position
     */
    public Position selectPositionsByPK(Integer PositionId);
	
}
