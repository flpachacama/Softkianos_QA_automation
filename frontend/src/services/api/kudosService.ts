import { apiClient } from './client';
import type { KudoFormData } from '../../schemas/kudoFormSchema';

export interface KudoHistoryItem {
  id: string;
  fromUser: string;
  toUser: string;
  category: string;
  message: string;
  points: number;
  createdAt: string;
}

/**
 * Service for Kudos related operations
 */
export const kudosService = {
  /**
   * Sends a kudo to the backend
   */
  send: async (payload: KudoFormData): Promise<void> => {
    const response = await apiClient.post('/v1/kudos', payload);
    if (response.status !== 202) {
      throw new Error(`Unexpected status: ${response.status}`);
    }
  },

  /**
   * Fetches previously sent kudos
   */
  fetchKudos: async (): Promise<KudoHistoryItem[]> => {
    const response = await apiClient.get<KudoHistoryItem[]>('/v1/kudos');
    if (response.status !== 200) {
      throw new Error(`Unexpected status: ${response.status}`);
    }

    if (!Array.isArray(response.data)) {
      throw new Error('Invalid response format for kudos history');
    }

    return response.data;
  }
};

export default kudosService;
