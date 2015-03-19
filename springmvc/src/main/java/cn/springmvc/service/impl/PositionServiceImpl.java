package cn.springmvc.service.impl;

import cn.springmvc.dao.PositionDAO;
import cn.springmvc.model.Position;
import cn.springmvc.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService{

	@Autowired
	private PositionDAO positionDAO;


    @Override
    public Position insertPosition(Position position) {
        return positionDAO.insertPosition(position);
    }

    @Override
    public void deletePosition(Position position) {
        positionDAO.deletePosition(position);
    }

    @Override
    public Position updatePosition(Position position) {
        return positionDAO.updatePosition(position);
    }

    @Override
    public List<Position> selectAllPositions() {
        return positionDAO.selectAllPositions();
    }

    @Override
    public List<Position> selectPositionsByCon(Position position) {
        return positionDAO.selectPositionsByCon(position);
    }

    @Override
    public Position selectPositionsByPK(Integer positionId) {
        return positionDAO.selectPositionsByPK(positionId);
    }
}
