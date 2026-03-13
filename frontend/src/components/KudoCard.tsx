import React from 'react';
import { MessageSquare, Star, Tag, UserRound, CalendarDays } from 'lucide-react';
import type { KudoHistoryItem } from '../services/api/kudosService';

interface KudoCardProps {
  kudo: KudoHistoryItem;
}

const formatDate = (value: string): string => {
  const parsed = new Date(value);
  if (Number.isNaN(parsed.getTime())) {
    return value;
  }

  return parsed.toLocaleString('es-CO', {
    dateStyle: 'medium',
    timeStyle: 'short',
  });
};

export const KudoCard: React.FC<KudoCardProps> = ({ kudo }) => {
  return (
    <article className="rounded-2xl border border-white/5 bg-zinc-900/40 p-5 shadow-2xl backdrop-blur-sm">
      <div className="flex flex-wrap items-start justify-between gap-3">
        <div className="space-y-2">
          <div className="flex items-center gap-2 text-zinc-300">
            <UserRound className="h-4 w-4 text-brand" />
            <p className="text-sm">
              <span className="text-zinc-500">De:</span>{' '}
              <span className="font-semibold text-white">{kudo.fromUser}</span>
            </p>
          </div>
          <p className="text-sm text-zinc-300">
            <span className="text-zinc-500">Para:</span>{' '}
            <span className="font-semibold text-white">{kudo.toUser}</span>
          </p>
        </div>

        <div className="rounded-full border border-brand/30 bg-brand/10 px-3 py-1 text-xs font-bold uppercase tracking-wide text-brand">
          {kudo.points} pts
        </div>
      </div>

      <div className="mt-4 grid gap-3 text-sm text-zinc-300">
        <div className="flex items-center gap-2">
          <Tag className="h-4 w-4 text-brand" />
          <span className="text-zinc-500">Categoria:</span>
          <span className="font-medium text-zinc-100">{kudo.category}</span>
        </div>

        <div className="flex items-start gap-2">
          <MessageSquare className="mt-0.5 h-4 w-4 text-brand" />
          <p className="leading-relaxed text-zinc-300">{kudo.message}</p>
        </div>

        <div className="flex items-center gap-2 text-xs text-zinc-400">
          <CalendarDays className="h-4 w-4" />
          <span>{formatDate(kudo.createdAt)}</span>
        </div>
      </div>

      <div className="mt-4 border-t border-white/5 pt-3">
        <p className="flex items-center gap-1 text-xs uppercase tracking-wide text-zinc-500">
          <Star className="h-3.5 w-3.5 text-brand" />
          Reconocimiento registrado
        </p>
      </div>
    </article>
  );
};

export default KudoCard;
