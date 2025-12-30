package com.example.cookwhatyouhave.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cookwhatyouhave.domain.model.MealCategory
import com.example.cookwhatyouhave.domain.model.otherCategories

@Composable
fun CategoryFilterSelection(
    selectedCategory: MealCategory?,
    onCategoryClick:(MealCategory)-> Unit,
    onSubCategoryClick:(String)-> Unit
){
    var expandedOther by remember { mutableStateOf(false) }
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(MealCategory.entries){category->
            Box{
                FilterChip(
                    selected = (category == selectedCategory),
                    onClick = {
                        if(category == MealCategory.OTHER){
                            expandedOther = true
                            onCategoryClick(category)
                        }else{
                            onCategoryClick(category)
                            expandedOther = false
                        }
                    },
                    label = {
                        Text(
                            text = category.mealName,
                            style = MaterialTheme.typography.labelLarge
                        )
                    },
                    leadingIcon =if(category == selectedCategory) {
                        {Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )}
                    } else null,
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color(0xFFFF9800).copy(alpha = 0.2f),
                        selectedLabelColor = Color(0xFFE65100)
                    )
                )
                if(category == MealCategory.OTHER){
                    DropdownMenu(
                        expanded = expandedOther
                        , onDismissRequest = {expandedOther = false},
                        modifier = Modifier.background(Color.White)
                            .heightIn(max = 300.dp)
                    ) {
                        otherCategories.forEach { subCategory ->
                            DropdownMenuItem(
                                text = {Text(subCategory)},
                                onClick = {onSubCategoryClick(subCategory)
                                expandedOther = false }
                            )
                        }
                    }
                }
            }
        }
    }

}