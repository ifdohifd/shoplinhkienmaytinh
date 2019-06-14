
package shoplinhkienmaytinh.catalog;

import shoplinhkienmaytinh.catalog.LinhKien.LKType;

import org.salespointframework.catalog.Catalog;
import org.springframework.data.domain.Sort;
public interface ShopCatalog extends Catalog<LinhKien> {

	static final Sort DEFAULT_SORT = Sort.by("productIdentifier").descending();

	/**
	 * Returns all {@link Disc}s by type ordered by the given {@link Sort}.
	 *
	 * @param type must not be {@literal null}.
	 * @param sort must not be {@literal null}.
	 * @return the discs of the given type, never {@literal null}.
	 */
	Iterable<LinhKien> findByType(LKType type, Sort sort);

	/**
	 * Returns all {@link Disc}s by type ordered by their identifier.
	 *
	 * @param type must not be {@literal null}.
	 * @return the discs of the given type, never {@literal null}.
	 */
	default Iterable<LinhKien> findByType(LKType type) {
		return findByType(type, DEFAULT_SORT);
	}
}
