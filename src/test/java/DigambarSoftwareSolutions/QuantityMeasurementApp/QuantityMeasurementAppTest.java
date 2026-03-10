package DigambarSoftwareSolutions.QuantityMeasurementApp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

private static final double EPSILON = 1e-6;

// Equality Test Cases
@Test
void testEquality_LitreToLitre_SameValue() {
    Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
    Quantity<VolumeUnit> v2 = new Quantity<>(1.0, VolumeUnit.LITRE);

    assertTrue(v1.equals(v2));
}

@Test
void testEquality_LitreToLitre_DifferentValue() {
    Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
    Quantity<VolumeUnit> v2 = new Quantity<>(2.0, VolumeUnit.LITRE);

    assertFalse(v1.equals(v2));
}

@Test
void testEquality_LitreToMillilitre_EquivalentValue() {
    Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
    Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

    assertTrue(v1.equals(v2));
}

@Test
void testEquality_MillilitreToLitre_EquivalentValue() {
    Quantity<VolumeUnit> v1 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
    Quantity<VolumeUnit> v2 = new Quantity<>(1.0, VolumeUnit.LITRE);

    assertTrue(v1.equals(v2));
}

// Conversion Test Cases
@Test
void testConversion_LitreToMillilitre() {
    Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.LITRE);

    Quantity<VolumeUnit> result = v.convertTo(VolumeUnit.MILLILITRE);

    assertEquals(1000.0, result.getValue(), EPSILON);
}

@Test
void testConversion_MillilitreToLitre() {
    Quantity<VolumeUnit> v = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

    Quantity<VolumeUnit> result = v.convertTo(VolumeUnit.LITRE);

    assertEquals(1.0, result.getValue(), EPSILON);
}

@Test
void testConversion_GallonToLitre() {
    Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.GALLON);

    Quantity<VolumeUnit> result = v.convertTo(VolumeUnit.LITRE);

    assertEquals(3.78541, result.getValue(), EPSILON);
}

// Addition Test Cases
@Test
void testAddition_SameUnit_LitrePlusLitre() {
    Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
    Quantity<VolumeUnit> v2 = new Quantity<>(2.0, VolumeUnit.LITRE);

    Quantity<VolumeUnit> result = v1.add(v2);

    assertEquals(3.0, result.getValue(), EPSILON);
}

@Test
void testAddition_CrossUnit_LitrePlusMillilitre() {
    Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
    Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

    Quantity<VolumeUnit> result = v1.add(v2);

    assertEquals(2.0, result.getValue(), EPSILON);
}

@Test
void testAddition_ExplicitTargetUnit_Millilitre() {
    Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
    Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

    Quantity<VolumeUnit> result = v1.add(v2, VolumeUnit.MILLILITRE);

    assertEquals(2000.0, result.getValue(), EPSILON);
}

// VolumeUnit Enum Test 
@Test
void testVolumeUnitEnum_LitreConstant() {
    assertEquals(1.0, VolumeUnit.LITRE.getConversionFactor(), EPSILON);
}

@Test
void testVolumeUnitEnum_MillilitreConstant() {
    assertEquals(0.001, VolumeUnit.MILLILITRE.getConversionFactor(), EPSILON);
}

@Test
void testVolumeUnitEnum_GallonConstant() {
    assertEquals(3.78541, VolumeUnit.GALLON.getConversionFactor(), EPSILON);
}

// Cross Category Test
@Test
void testEquality_VolumeVsLength_Incompatible() {
    Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
    Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);

    assertFalse(volume.equals(length));
}

// Constructor Validation
@Test
void testEquality_NullUnit() {
    assertThrows(IllegalArgumentException.class, () -> {
        new Quantity<>(1.0, null);
    });
}

}