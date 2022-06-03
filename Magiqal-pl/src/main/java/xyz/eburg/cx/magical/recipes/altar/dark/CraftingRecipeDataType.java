package xyz.eburg.cx.magical.recipes.altar.dark;

import org.apache.commons.lang.SerializationUtils;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class CraftingRecipeDataType implements PersistentDataType<byte[], CraftingRecipe> {
  @Override
  public @NotNull Class<byte[]> getPrimitiveType() {
    return byte[].class;
  }

  @Override
  public @NotNull Class<CraftingRecipe> getComplexType() {
    return CraftingRecipe.class;
  }

  @Override
  public byte @NotNull [] toPrimitive(@NotNull CraftingRecipe complex, @NotNull PersistentDataAdapterContext context) {
    return SerializationUtils.serialize(complex);      /* Autogenerated: return new byte[0];*/
  }

  @Override
  public @NotNull CraftingRecipe fromPrimitive(byte @NotNull [] primitive, @NotNull PersistentDataAdapterContext context) {
    try {
      InputStream is = new ByteArrayInputStream(primitive);
      ObjectInputStream ois = new ObjectInputStream(is);
      return (CraftingRecipe) ois.readObject();
    }catch (IOException | ClassNotFoundException e){
      e.printStackTrace();
    }
    return null;
  }
}