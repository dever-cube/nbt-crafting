/*
 * Copyright 2020-2022 Siphalor
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied.
 * See the License for the specific language governing
 * permissions and limitations under the License.
 */

package de.siphalor.nbtcrafting.mixin.network;

import java.util.Collection;
import java.util.List;

import net.minecraft.network.packet.s2c.play.SynchronizeRecipesS2CPacket;
import net.minecraft.recipe.Recipe;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import de.siphalor.nbtcrafting.api.ServerRecipe;

@Mixin(SynchronizeRecipesS2CPacket.class)
public abstract class MixinSynchronizeRecipesS2CPacket {
	@Final
	@Shadow
	private List<Recipe<?>> recipes;

	@Inject(method = "<init>(Ljava/util/Collection;)V", at = @At("RETURN"))
	public void onCreated(Collection<Recipe<?>> recipes, CallbackInfo ci) {
		this.recipes.removeIf(recipe -> recipe instanceof ServerRecipe);
	}
}
