package learning.genetic;

import dto.Position;

/**
 * Created on 14.11.2015.
 *
 * @author Źmicier Dzikański
 */
public interface PositionEstimator  {
    float[] getCoeffs();
    void setCoeffs(float[] coeffs);
    float estimatePosition(Position p);
}
