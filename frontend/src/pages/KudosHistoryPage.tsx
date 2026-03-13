import React, { useEffect, useMemo, useState } from 'react';
import { Search, RefreshCcw } from 'lucide-react';
import KudoCard from '../components/KudoCard';
import { kudosService } from '../services';
import type { KudoHistoryItem } from '../services/api/kudosService';

const KudosHistoryPage: React.FC = () => {
  const [kudos, setKudos] = useState<KudoHistoryItem[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [searchTerm, setSearchTerm] = useState('');
  const [categoryFilter, setCategoryFilter] = useState('all');

  const loadKudos = async () => {
    setLoading(true);
    setError(null);

    try {
      const response = await kudosService.fetchKudos();
      setKudos(response);
    } catch {
      setError('No pudimos cargar el historial de Kudos. Intenta nuevamente.');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    void loadKudos();
  }, []);

  const categories = useMemo(
    () => Array.from(new Set(kudos.map((kudo) => kudo.category))),
    [kudos]
  );

  const filteredKudos = useMemo(() => {
    return kudos.filter((kudo) => {
      const searchValue = searchTerm.trim().toLowerCase();
      const matchesSearch =
        !searchValue ||
        kudo.fromUser.toLowerCase().includes(searchValue) ||
        kudo.toUser.toLowerCase().includes(searchValue);

      const matchesCategory =
        categoryFilter === 'all' || kudo.category === categoryFilter;

      return matchesSearch && matchesCategory;
    });
  }, [kudos, searchTerm, categoryFilter]);

  return (
    <div className="min-h-screen">
      <div className="fixed inset-0 -z-10 bg-[radial-gradient(circle_at_top,_var(--tw-gradient-stops))] from-brand/10 via-zinc-950 to-zinc-950" />

      <div className="mx-auto w-full max-w-6xl px-4 py-28 sm:px-6">
        <div className="mb-10 text-center">
          <h2 className="text-3xl font-bold tracking-tight text-white sm:text-5xl">
            Historial de <span className="text-brand">Kudos</span>
          </h2>
          <p className="mx-auto mt-4 max-w-2xl text-zinc-400">
            Consulta los reconocimientos enviados por el equipo y revisa su detalle.
          </p>
        </div>

        <section className="mb-8 rounded-2xl border border-white/5 bg-zinc-900/40 p-4 backdrop-blur-sm sm:p-5">
          <div className="grid gap-3 md:grid-cols-[1fr_220px_auto]">
            <label className="relative">
              <Search className="pointer-events-none absolute left-3 top-1/2 h-4 w-4 -translate-y-1/2 text-zinc-500" />
              <input
                type="text"
                value={searchTerm}
                onChange={(event) => setSearchTerm(event.target.value)}
                placeholder="Buscar por remitente o destinatario"
                className="w-full rounded-xl border border-white/10 bg-zinc-900/70 py-2.5 pl-10 pr-4 text-sm text-zinc-100 placeholder:text-zinc-500 focus:border-brand focus:outline-none"
              />
            </label>

            <select
              value={categoryFilter}
              onChange={(event) => setCategoryFilter(event.target.value)}
              className="rounded-xl border border-white/10 bg-zinc-900/70 px-4 py-2.5 text-sm text-zinc-100 focus:border-brand focus:outline-none"
            >
              <option value="all">Todas las categorias</option>
              {categories.map((category) => (
                <option key={category} value={category}>
                  {category}
                </option>
              ))}
            </select>

            <button
              type="button"
              onClick={() => void loadKudos()}
              className="inline-flex items-center justify-center gap-2 rounded-xl border border-white/10 bg-zinc-900/70 px-4 py-2.5 text-sm font-semibold text-zinc-200 transition-colors hover:border-brand hover:text-white"
            >
              <RefreshCcw className="h-4 w-4" />
              Actualizar
            </button>
          </div>
        </section>

        {loading && (
          <div className="grid gap-4 md:grid-cols-2">
            {[...Array.from({ length: 4 })].map((_, index) => (
              <div
                key={index}
                className="h-48 animate-pulse rounded-2xl border border-white/5 bg-zinc-900/40"
              />
            ))}
          </div>
        )}

        {!loading && error && (
          <div className="rounded-2xl border border-red-500/30 bg-red-500/10 p-6 text-center">
            <p className="text-red-200">{error}</p>
            <button
              type="button"
              onClick={() => void loadKudos()}
              className="mt-4 rounded-lg border border-red-400/50 px-4 py-2 text-sm font-semibold text-red-200 hover:bg-red-500/10"
            >
              Reintentar
            </button>
          </div>
        )}

        {!loading && !error && kudos.length === 0 && (
          <div className="rounded-2xl border border-white/10 bg-zinc-900/40 p-8 text-center">
            <p className="text-lg font-semibold text-zinc-100">Aun no hay Kudos enviados.</p>
            <p className="mt-2 text-sm text-zinc-400">Cuando el equipo envie reconocimientos, apareceran aqui.</p>
          </div>
        )}

        {!loading && !error && kudos.length > 0 && filteredKudos.length === 0 && (
          <div className="rounded-2xl border border-white/10 bg-zinc-900/40 p-8 text-center">
            <p className="text-lg font-semibold text-zinc-100">No encontramos resultados.</p>
            <p className="mt-2 text-sm text-zinc-400">Prueba con otro nombre o ajusta el filtro de categoria.</p>
          </div>
        )}

        {!loading && !error && filteredKudos.length > 0 && (
          <section className="grid gap-4 md:grid-cols-2">
            {filteredKudos.map((kudo) => (
              <KudoCard key={kudo.id} kudo={kudo} />
            ))}
          </section>
        )}
      </div>
    </div>
  );
};

export default KudosHistoryPage;
