package com.vshkl.beerstore5.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vshkl.beerstore5.R

@Composable
fun BeerParameters(
    abv: Double,
    ibu: Double,
    ebc: Double,
    srm: Double,
    ph: Double,
    attenuationLevel: Double,
    modifier: Modifier = Modifier,
) {
    val rowModifier = remember {
        Modifier
            .padding(bottom = 4.dp)
            .fillMaxWidth()
    }
    val lastRowModifier = remember {
        Modifier.fillMaxWidth()
    }

    Column(modifier = modifier) {
        BeerParametersRow(
            label = stringResource(id = R.string.label_abv),
            value = abv,
            modifier = rowModifier,
        )
        BeerParametersRow(
            label = stringResource(id = R.string.label_ibu),
            value = ibu,
            modifier = rowModifier,
        )
        BeerParametersRow(
            label = stringResource(id = R.string.label_ebc),
            value = ebc,
            modifier = rowModifier,
        )
        BeerParametersRow(
            label = stringResource(id = R.string.label_srm),
            value = srm,
            modifier = rowModifier,
        )
        BeerParametersRow(
            label = stringResource(id = R.string.label_ph),
            value = ph,
            modifier = rowModifier,
        )
        BeerParametersRow(
            label = stringResource(id = R.string.label_attenuation_level),
            value = attenuationLevel,
            modifier = lastRowModifier,
        )
    }
}

@Preview
@Composable
fun BeerParametersPreview() {
    BeerParameters(
        abv = 5.0,
        ibu = 10.0,
        ebc = 20.0,
        srm = 30.0,
        ph = 4.0,
        attenuationLevel = 50.0,
    )
}
