/**
 */
package nosql.impl;

import java.util.Collection;

import nosql.Cell;
import nosql.Column;
import nosql.NosqlPackage;
import nosql.Row;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Row</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link nosql.impl.RowImpl#getCells <em>Cells</em>}</li>
 *   <li>{@link nosql.impl.RowImpl#getAdditionalColumns <em>Additional Columns</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RowImpl extends ColumnFamilyImpl implements Row {
	/**
	 * The cached value of the '{@link #getCells() <em>Cells</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCells()
	 * @generated
	 * @ordered
	 */
	protected EList cells;

	/**
	 * The cached value of the '{@link #getAdditionalColumns() <em>Additional Columns</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdditionalColumns()
	 * @generated
	 * @ordered
	 */
	protected EList additionalColumns;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RowImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return NosqlPackage.Literals.ROW;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getCells() {
		if (cells == null) {
			cells = new EObjectContainmentEList(Cell.class, this, NosqlPackage.ROW__CELLS);
		}
		return cells;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getAdditionalColumns() {
		if (additionalColumns == null) {
			additionalColumns = new EObjectContainmentEList(Column.class, this, NosqlPackage.ROW__ADDITIONAL_COLUMNS);
		}
		return additionalColumns;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case NosqlPackage.ROW__CELLS:
				return ((InternalEList)getCells()).basicRemove(otherEnd, msgs);
			case NosqlPackage.ROW__ADDITIONAL_COLUMNS:
				return ((InternalEList)getAdditionalColumns()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case NosqlPackage.ROW__CELLS:
				return getCells();
			case NosqlPackage.ROW__ADDITIONAL_COLUMNS:
				return getAdditionalColumns();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case NosqlPackage.ROW__CELLS:
				getCells().clear();
				getCells().addAll((Collection)newValue);
				return;
			case NosqlPackage.ROW__ADDITIONAL_COLUMNS:
				getAdditionalColumns().clear();
				getAdditionalColumns().addAll((Collection)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case NosqlPackage.ROW__CELLS:
				getCells().clear();
				return;
			case NosqlPackage.ROW__ADDITIONAL_COLUMNS:
				getAdditionalColumns().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case NosqlPackage.ROW__CELLS:
				return cells != null && !cells.isEmpty();
			case NosqlPackage.ROW__ADDITIONAL_COLUMNS:
				return additionalColumns != null && !additionalColumns.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //RowImpl
