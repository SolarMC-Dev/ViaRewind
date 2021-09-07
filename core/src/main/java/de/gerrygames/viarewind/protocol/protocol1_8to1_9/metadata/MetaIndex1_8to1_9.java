package de.gerrygames.viarewind.protocol.protocol1_8to1_9.metadata;

import com.viaversion.viaversion.api.minecraft.entities.Entity1_10Types;
import com.viaversion.viaversion.protocols.protocol1_9to1_8.metadata.MetaIndex;
import com.viaversion.viaversion.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MetaIndex1_8to1_9 {

// Solar start - make map immutable
	private static final Map<Pair<Entity1_10Types.EntityType, Integer>, MetaIndex> METADATA_REWRITES;

	static {
		Map<Pair<Entity1_10Types.EntityType, Integer>, MetaIndex> metadataRewrites = new HashMap<>();
		for (MetaIndex index : MetaIndex.values())
			metadataRewrites.put(new Pair<>(index.getClazz(), index.getNewIndex()), index);
		METADATA_REWRITES = Map.copyOf(metadataRewrites);
	}

	private static Optional<MetaIndex> getIndex(Entity1_10Types.EntityType type, int index) {
		Pair pair = new Pair<>(type, index);
		return Optional.ofNullable(METADATA_REWRITES.get(pair)); /*
		if (metadataRewrites.containsKey(pair)) {
			return Optional.of(metadataRewrites.get(pair));
		}

		return Optional.empty();
*/ // Solar end
	}

	public static MetaIndex searchIndex(Entity1_10Types.EntityType type, int index) {
		Entity1_10Types.EntityType currentType = type;
		do {
			Optional<MetaIndex> optMeta = getIndex(currentType, index);

			if (optMeta.isPresent()) {
				return optMeta.get();
			}

			currentType = currentType.getParent();
		} while (currentType != null);

		return null;
	}

}
