/*
 * Copyright (c) 2015 Villu Ruusmann
 *
 * This file is part of JPMML-SkLearn
 *
 * JPMML-SkLearn is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JPMML-SkLearn is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with JPMML-SkLearn.  If not, see <http://www.gnu.org/licenses/>.
 */
package sklearn.ensemble;

import java.util.List;

import org.dmg.pmml.DataField;
import org.dmg.pmml.MiningFunctionType;
import org.dmg.pmml.MiningModel;
import org.dmg.pmml.MultipleModelMethodType;
import sklearn.Regressor;
import sklearn.tree.DecisionTreeRegressor;
import sklearn.tree.TreeConverter;

public class RandomForestRegressor extends Regressor {

	public RandomForestRegressor(String module, String name){
		super(module, name);
	}

	@Override
	public MiningModel encodeModel(List<DataField> dataFields){
		List<DecisionTreeRegressor> estimators = getEstimators();

		return TreeConverter.encodeTreeModelEnsemble(estimators, null, MultipleModelMethodType.AVERAGE, MiningFunctionType.REGRESSION, dataFields);
	}

	@SuppressWarnings (
		value = {"unchecked"}
	)
	public List<DecisionTreeRegressor> getEstimators(){
		return (List<DecisionTreeRegressor>)get("estimators_");
	}
}